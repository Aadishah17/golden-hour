import sys
import subprocess
from pathlib import Path

# Create graphify-out dir if it doesn't exist
out_dir = Path("graphify-out")
out_dir.mkdir(exist_ok=True)

# Write the interpreter path to .graphify_python
python_path = Path(sys.executable)
(out_dir / ".graphify_python").write_text(str(python_path), encoding="utf-8")

# Check if graphifyy is installed, if not try to install it
try:
    import graphify
    print("graphifyy is already installed.")
except ImportError:
    print("graphifyy is not installed. Installing via pip...")
    try:
        subprocess.run([sys.executable, "-m", "pip", "install", "graphifyy", "-q"], check=True)
        print("graphifyy successfully installed!")
    except subprocess.CalledProcessError as e:
        print(f"Failed to install graphifyy: {e}")
        sys.exit(1)

# Save scan root path
root_path = Path(".").resolve()
(out_dir / ".graphify_root").write_text(str(root_path), encoding="utf-8")
print("Setup complete.")
