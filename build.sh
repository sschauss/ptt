cd ./ass01-client
echo "ass01-client: Node.js dependencies..."
npm install
echo
echo
echo
echo "sas01-client: install bower dependencies..."
bower install
echo
echo
echo
echo "ass01-client: build webapp..."
grunt build
echo
echo
echo
echo "ass01-client: copy webapp to servers assets folder"
cp -R ./dist ../ass01-server/src/main/resources/assets
echo
echo
echo
echo "ass01-server: build server"
cd ../ass01-server
maven install
echo
echo
echo
cd ../ass02
echo "build SCalaSS..."
sbt assembly
echo
echo
echo
cd ./ass03-client
echo "ass03-client: Node.js dependencies..."
npm install
echo
echo
echo
echo "sas03-client: install bower dependencies..."
bower install
echo
echo
echo
echo "ass03-client: build webapp..."
grunt build
echo
echo
echo
echo "ass03-client: copy webapp to servers assets folder"
cp -R ./dist ../ass03-server/src/main/resources/assets
echo
echo
echo
echo "ass03-server: build server"
cd ../ass03-server
maven install
echo
echo
echo


