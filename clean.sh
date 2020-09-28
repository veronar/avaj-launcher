#!/bin/sh
echo "Removing all class files..."
rm -rf ./Main.class
rm -rf ./simulator/interfaces/Flyable.class
rm -rf ./simulator/vehicles/*.class
rm -rf ./simulator/io/*.class
rm -rf ./simulator/weather/WeatherProvider.class
rm -rf ./simulator/*.class
