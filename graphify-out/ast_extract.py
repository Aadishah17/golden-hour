import sys
import json
from graphify.extract import collect_files, extract
from pathlib import Path

def main():
    code_files = []
    # Load detected files
    detect_path = Path('graphify-out/.graphify_detect.json')
    if not detect_path.exists():
        print("Error: Detection file not found.")
        sys.exit(1)

    detect = json.loads(detect_path.read_text(encoding="utf-8"))
    for f in detect.get('files', {}).get('code', []):
        file_path = Path(f)
        code_files.extend(collect_files(file_path) if file_path.is_dir() else [file_path])

    if code_files:
        # Extract AST structure using parallel=False on Windows if needed, 
        # but wrapping in __main__ allows parallel extraction to succeed safely.
        result = extract(code_files, cache_root=Path('.'))
        # Ensure graphify-out exists
        Path('graphify-out').mkdir(exist_ok=True)
        Path('graphify-out/.graphify_ast.json').write_text(
            json.dumps(result, indent=2, ensure_ascii=False), 
            encoding="utf-8"
        )
        print(f'AST: {len(result["nodes"])} nodes, {len(result["edges"])} edges')
    else:
        empty_result = {'nodes': [], 'edges': [], 'input_tokens': 0, 'output_tokens': 0}
        Path('graphify-out/.graphify_ast.json').write_text(
            json.dumps(empty_result, ensure_ascii=False), 
            encoding="utf-8"
        )
        print('No code files - skipping AST extraction')

if __name__ == '__main__':
    main()
