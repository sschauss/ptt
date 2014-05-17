cd ./ass01-client
echo "install Node.js dependencies..."
npm install
echo
echo
echo
echo "install bower dependencies..."
bower install
echo
echo
echo
echo "build webapp..."
grunt build
echo
echo
echo
echo "copy webapp to servers assets folder"
cp -R ./dist ../ass01-server/src/main/resources/assets

