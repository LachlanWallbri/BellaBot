import React from 'react';
import { Dispatch, SetStateAction, useEffect, useState } from 'react';
import { View, StyleSheet, Text, Pressable, NativeModules, } from 'react-native';
import NavBar from '../components/NavBar';
import { ChartConfigType, buttonLabelType, graphDataUnit, graphDataType } from '../utils/types';
import { ANGULAR_SPEED_CHART_CONFIG, BATTERY_CHART_CONFIG, BELLABOT_BLUE, LINEAR_SPEED_CHART_CONFIG, NUM_VALUES_TO_AVERAGE, POLLING_RATE } from '../utils/constants';
import { LineChart } from 'react-native-gifted-charts';
import { averageFirstNValues } from '../utils/helpers';

const { CANBusModule } = NativeModules;

const GraphsScreen = (): JSX.Element => {
  // Originally, these useState's were in the DataContextComponent, however
  // the performance degraded. Specifically, the graph would be delayed by
  // about 1 second from performing the action. Now it's responsive.

  const [batteryData, setBatteryData] = useState<graphDataType[]>([]);
  const [linearSpeedData, setLinearSpeedData] = useState<graphDataType[]>([]);
  const [angularSpeedData, setAngularSpeedData] = useState<graphDataType[]>([]);

  const [buttonPressed, setButtonPressed] = useState<buttonLabelType>("Battery Percent");
  const [dataUnit, setDataUnit] = useState<graphDataUnit>("(%)");

  const [currentData, setCurrentData] = useState<graphDataType[]>(batteryData);
  const [currentChartConfig, setCurrentChartConfig] = useState<ChartConfigType>(BATTERY_CHART_CONFIG);

  const addDataPoint = (val: number, setDataFn: Dispatch<SetStateAction<graphDataType[]>>) => {
    const newObj = { value: val, dataPointText: String(val) };
    setDataFn(prevData => [...averageFirstNValues(prevData, NUM_VALUES_TO_AVERAGE), newObj]);
  }

  // Use Effect here is for updating the lists with new values
  // Mirrors the functionality in DataContextComponent
  useEffect(() => {
    const interval = setInterval(() => {
      const updateData = async () => {
        try {
          switch (buttonPressed) {
            case 'Battery Percent': {
              const batteryPercentRes: number = await CANBusModule.getBatteryPercent();
              addDataPoint(parseFloat(batteryPercentRes.toFixed(1)), setBatteryData);
              break;
            }
            case 'Linear Speed': {
              const linSpeedRes: number = await CANBusModule.getLinearSpeed();
              addDataPoint(parseFloat(linSpeedRes.toFixed(1)), setLinearSpeedData);
              break;
            }
            case 'Angular Speed': {
              const angSpeedRes: number = await CANBusModule.getAngularSpeed();
              addDataPoint(parseFloat(angSpeedRes.toFixed(1)), setAngularSpeedData);
              break;
            }
          }

        } catch (error) {
          console.log("An error occurred: " + error)
        }
      }

      void updateData();
    }, POLLING_RATE);

    return () => clearInterval(interval);
  }, [buttonPressed]);


  // Changing which data is shown depending on the button pressed
  useEffect(() => {
    switch (buttonPressed) {
      case "Battery Percent":
        setCurrentData(batteryData);
        setDataUnit("(%)");
        setCurrentChartConfig(BATTERY_CHART_CONFIG);
        break;
      case "Linear Speed":
        setCurrentData(linearSpeedData);
        setDataUnit("(m/s)");
        setCurrentChartConfig(LINEAR_SPEED_CHART_CONFIG);
        break;
      case "Angular Speed":
        setCurrentData(angularSpeedData);
        setDataUnit("(rad/s)");
        setCurrentChartConfig(ANGULAR_SPEED_CHART_CONFIG);
        break;
    }
  }, [buttonPressed, batteryData, linearSpeedData, angularSpeedData]);

  return (
    <View style={styles.container}>
      <NavBar />
      <View style={styles.chartContainer}>
        <View style={styles.lineChartContainer}>
          <Text testID='graph-title' style={styles.title}>{buttonPressed} {dataUnit}</Text>
          <View
            style={{
              borderRadius: 15,
              overflow: 'hidden',
              backgroundColor: BELLABOT_BLUE,
              padding: 5,
            }}
          >
            <LineChart
              initialSpacing={5}
              yAxisExtraHeight={20}
              height={currentChartConfig.height}
              width={900}
              data={currentData}
              spacing={30}
              textColor1="black"
              maxValue={currentChartConfig.maxValue}
              mostNegativeValue={currentChartConfig.minValue}
              textShiftY={-15}
              stepValue={currentChartConfig.stepVal}
              rotateLabel
              textShiftX={-25}
              textFontSize={currentChartConfig.fontSize}
              thickness={4}
              hideRules={currentChartConfig.hideRules}
              focusEnabled
              yAxisColor="#0BA5A4"
              xAxisColor="#0BA5A4"
              showVerticalLines={currentChartConfig.showVerticalLines}
              verticalLinesColor="rgba(14,164,164,0.5)"
              yAxisLabelTexts={currentChartConfig.yAxisLabels}
              color="#FFFFFF"
            />
          </View>
        </View>
        <View style={styles.sideContainer}>
          <Pressable
            style={[styles.button, buttonPressed === "Battery Percent" && styles.buttonPressed]}
            onPress={() => setButtonPressed("Battery Percent")}
            testID='graph-battery-btn'
          >
            <Text style={buttonPressed === "Battery Percent" ? styles.pressedButtonText : styles.buttonText}>Battery</Text>
            <Text style={buttonPressed === "Battery Percent" ? styles.pressedButtonText : styles.buttonText}>Percent</Text>
          </Pressable>

          <Pressable
            style={[styles.button, buttonPressed === "Linear Speed" && styles.buttonPressed]}
            onPress={() => setButtonPressed("Linear Speed")}
            testID='graph-lin-spd-btn'
          >
            <Text style={buttonPressed === "Linear Speed" ? styles.pressedButtonText : styles.buttonText}>Linear</Text>
            <Text style={buttonPressed === "Linear Speed" ? styles.pressedButtonText : styles.buttonText}>Speed</Text>
          </Pressable>

          <Pressable
            style={[styles.button, buttonPressed === "Angular Speed" && styles.buttonPressed]}
            onPress={() => setButtonPressed("Angular Speed")}
            testID='graph-ang-spd-btn'
          >
            <Text style={buttonPressed === "Angular Speed" ? styles.pressedButtonText : styles.buttonText}>Angular</Text>
            <Text style={buttonPressed === "Angular Speed" ? styles.pressedButtonText : styles.buttonText}>Speed</Text>
          </Pressable>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  title: {
    fontSize: 24,
    fontFamily: "Outfit-Regular",
  },
  chartContainer: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 15,
  },
  sideContainer: {
    width: '20%',
    height: "100%",
    justifyContent: 'space-around',
    alignItems: 'center',
    marginTop: 20,
    marginBottom: 0,
  },
  lineChartContainer: {
    width: '80%',
    height: '100%',
    justifyContent: 'space-evenly',
    alignItems: 'center',
  },
  button: {
    backgroundColor: BELLABOT_BLUE,
    width: 200,
    height: 105,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 5,
    marginVertical: 5,
  },
  buttonPressed: {
    backgroundColor: '#fff',
    borderWidth: 4,
    color: BELLABOT_BLUE,
    borderColor: BELLABOT_BLUE
  },
  buttonText: {
    fontSize: 20,
    textAlign: 'center',
    color: "#fff",
    fontWeight: "bold",
    fontFamily: "Outfit-Regular"
  },
  pressedButtonText: {
    fontSize: 20,
    textAlign: 'center',
    color: BELLABOT_BLUE,
    fontWeight: "bold",
    fontFamily: "Outfit-Regular"
  },
});

export default GraphsScreen;
