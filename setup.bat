git clone https://github.com/xinntao/Real-ESRGAN.git
cd Real-ESRGAN

pip install torch torchvision torchaudio

# Install basicsr - https://github.com/xinntao/BasicSR
# We use BasicSR for both training and inference
pip install basicsr
# facexlib and gfpgan are for face enhancement
pip install facexlib
pip install gfpgan
pip install -r requirements.txt
python setup.py develop

pause
exit /b