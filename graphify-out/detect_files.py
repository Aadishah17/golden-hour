import json
from graphify.detect import detect
from pathlib import Path

# Run detect on the current directory
result = detect(Path('.'))

# Write to .graphify_detect.json
detect_json_path = Path('graphify-out/.graphify_detect.json')
detect_json_path.write_text(json.dumps(result, ensure_ascii=False, indent=2), encoding='utf-8')

print("Detection complete.")
