FROM tracer0tong/android-emulator:latest

RUN apt-get update -y
RUN wget -qO- https://deb.nodesource.com/setup_10.x | bash -
RUN  apt-get install -y nodejs
RUN apt-get install -y build-essential
RUN npm install appium
RUN ls -la node_modules


CMD "node_modules/.bin/appium && adb devices && /entrypoint.sh"

# RUN wget https://github.com/appium/appium-desktop/releases/download/v1.7.1/appium-desktop-1.7.1-x86_64.AppImage
# RUN chmod 755 appium-desktop-1.7.1-x86_64.AppImage
# RUN ./appium-desktop-1.7.1-x86_64.AppImage

# apt-get install -y libc6-i386 lib32stdc++6 lib32gcc1 lib32ncurses5 lib32z1
# https://stackoverflow.com/questions/4386392/aapt-not-found-under-the-right-path --> https://stackoverflow.com/questions/23182765/how-to-install-ia32-libs-in-ubuntu-14-04-lts-trusty-tahr  ---> apt-get install -y libc6-i386 lib32stdc++6 lib32gcc1 lib32ncurses5 lib32z1
