import sys
import json
from pathlib import Path
from graphify.build import build_from_json
from graphify.cluster import cluster, score_all
from graphify.analyze import god_nodes, surprising_connections, suggest_questions
from graphify.report import generate
from graphify.export import to_json

def main():
    extraction_path = Path('graphify-out/.graphify_extract.json')
    detection_path = Path('graphify-out/.graphify_detect.json')
    
    if not extraction_path.exists() or not detection_path.exists():
        print("Error: Required intermediate files not found.")
        sys.exit(1)

    extraction = json.loads(extraction_path.read_text(encoding="utf-8"))
    detection  = json.loads(detection_path.read_text(encoding="utf-8"))

    print("Building graph...")
    G = build_from_json(extraction)
    
    if G.number_of_nodes() == 0:
        print('ERROR: Graph is empty - extraction produced no nodes.')
        sys.exit(1)

    print("Clustering communities...")
    communities = cluster(G)
    cohesion = score_all(G, communities)
    
    tokens = {
        'input': extraction.get('input_tokens', 0), 
        'output': extraction.get('output_tokens', 0)
    }
    
    print("Analyzing graph metrics...")
    gods = god_nodes(G)
    surprises = surprising_connections(G, communities)
    labels = {cid: 'Community ' + str(cid) for cid in communities}
    
    # Generate suggested questions based on communities
    questions = suggest_questions(G, communities, labels)

    input_path_str = str(Path('.').resolve())
    print("Generating GRAPH_REPORT.md...")
    report = generate(
        G, communities, cohesion, labels, gods, surprises, 
        detection, tokens, input_path_str, suggested_questions=questions
    )
    
    Path('graphify-out/GRAPH_REPORT.md').write_text(report, encoding="utf-8")
    
    print("Saving graph.json...")
    to_json(G, communities, 'graphify-out/graph.json')

    analysis = {
        'communities': {str(k): v for k, v in communities.items()},
        'cohesion': {str(k): v for k, v in cohesion.items()},
        'gods': gods,
        'surprises': surprises,
        'questions': questions,
    }
    Path('graphify-out/.graphify_analysis.json').write_text(
        json.dumps(analysis, indent=2, ensure_ascii=False), 
        encoding="utf-8"
    )
    
    print(f'Graph: {G.number_of_nodes()} nodes, {G.number_of_edges()} edges, {len(communities)} communities')

if __name__ == '__main__':
    main()
