import sys
import json
from pathlib import Path

def main():
    ast_path = Path('graphify-out/.graphify_ast.json')
    if not ast_path.exists():
        print("Error: AST extraction file not found.")
        sys.exit(1)

    ast = json.loads(ast_path.read_text(encoding="utf-8"))
    
    # Load semantic if exists, otherwise construct empty
    sem_path = Path('graphify-out/.graphify_semantic.json')
    if sem_path.exists():
        sem = json.loads(sem_path.read_text(encoding="utf-8"))
    else:
        sem = {'nodes': [], 'edges': [], 'hyperedges': [], 'input_tokens': 0, 'output_tokens': 0}
        sem_path.write_text(json.dumps(sem, indent=2, ensure_ascii=False), encoding="utf-8")

    # Merge nodes deduplicated by id
    seen = {n['id'] for n in ast['nodes']}
    merged_nodes = list(ast['nodes'])
    for n in sem.get('nodes', []):
        if n['id'] not in seen:
            merged_nodes.append(n)
            seen.add(n['id'])

    merged_edges = ast['edges'] + sem.get('edges', [])
    merged_hyperedges = sem.get('hyperedges', [])
    
    merged = {
        'nodes': merged_nodes,
        'edges': merged_edges,
        'hyperedges': merged_hyperedges,
        'input_tokens': sem.get('input_tokens', 0),
        'output_tokens': sem.get('output_tokens', 0),
    }
    
    Path('graphify-out/.graphify_extract.json').write_text(
        json.dumps(merged, indent=2, ensure_ascii=False), 
        encoding="utf-8"
    )
    
    print(f'Merged: {len(merged_nodes)} nodes, {len(merged_edges)} edges ({len(ast["nodes"])} AST + {len(sem.get("nodes", []))} semantic)')

if __name__ == '__main__':
    main()
