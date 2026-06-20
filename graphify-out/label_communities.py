import sys
import json
from pathlib import Path
from graphify.build import build_from_json
from graphify.cluster import score_all
from graphify.analyze import god_nodes, surprising_connections, suggest_questions
from graphify.report import generate

def main():
    extraction_path = Path('graphify-out/.graphify_extract.json')
    detection_path = Path('graphify-out/.graphify_detect.json')
    analysis_path = Path('graphify-out/.graphify_analysis.json')
    
    if not all(p.exists() for p in [extraction_path, detection_path, analysis_path]):
        print("Error: Missing prior analysis files.")
        sys.exit(1)

    extraction = json.loads(extraction_path.read_text(encoding="utf-8"))
    detection  = json.loads(detection_path.read_text(encoding="utf-8"))
    analysis   = json.loads(analysis_path.read_text(encoding="utf-8"))

    G = build_from_json(extraction)
    
    # Parse communities and cohesion from analysis
    communities = {int(k): v for k, v in analysis['communities'].items()}
    cohesion = {int(k): v for k, v in analysis['cohesion'].items()}
    tokens = {
        'input': extraction.get('input_tokens', 0), 
        'output': extraction.get('output_tokens', 0)
    }

    # Custom community labels based on analyzed entities
    labels = {
        0: "Core UI and Navigation Framework",
        1: "Emergency Models and Repositories",
        2: "SOS and Samaritan Components",
        3: "Screen Destination Definitions",
        4: "Dependency Injection and Configuration",
        5: "Patient Triage Business Logic",
        6: "Hospital Dashboard Views",
        7: "Dashboard State Management",
        8: "Alerting State Management",
        9: "Samaritan State Management",
        10: "Language State Management",
        11: "SOS State Management",
        12: "Application Entry Point",
        13: "Root Build Script",
        14: "Project Settings Setup",
        15: "App Module Build Config",
        16: "Theme Color Scheme",
        17: "Theme Typography"
    }

    # Regenerate suggested questions with customized community labels
    questions = suggest_questions(G, communities, labels)

    input_path_str = str(Path('.').resolve())
    print("Regenerating GRAPH_REPORT.md with community labels...")
    report = generate(
        G, communities, cohesion, labels, analysis['gods'], analysis['surprises'], 
        detection, tokens, input_path_str, suggested_questions=questions
    )
    
    Path('graphify-out/GRAPH_REPORT.md').write_text(report, encoding="utf-8")
    
    # Save the labels to .graphify_labels.json
    labels_out = {str(k): v for k, v in labels.items()}
    Path('graphify-out/.graphify_labels.json').write_text(
        json.dumps(labels_out, indent=2, ensure_ascii=False), 
        encoding="utf-8"
    )
    
    # Save updated analysis with new suggested questions
    analysis['questions'] = questions
    analysis_path.write_text(json.dumps(analysis, indent=2, ensure_ascii=False), encoding="utf-8")
    
    print('Report updated with community labels.')

if __name__ == '__main__':
    main()
