FROM tracer0tong/android-emulator:latest

RUN apt-get update -y
RUN wget -qO- https://deb.nodesource.com/setup_10.x | bash -
RUN  apt-get install -y nodejs
RUN apt-get install -y build-essential
RUN npm install appium
RUN ls -la node_modules


CMD "node_modules/.bin/appium && /entrypoint.sh"

# RUN wget https://github.com/appium/appium-desktop/releases/download/v1.7.1/appium-desktop-1.7.1-x86_64.AppImage
# RUN chmod 755 appium-desktop-1.7.1-x86_64.AppImage
# RUN ./appium-desktop-1.7.1-x86_64.AppImage