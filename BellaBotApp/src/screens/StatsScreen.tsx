import React, { useContext } from 'react';
import { View, StyleSheet, Text } from 'react-native';
import NavBar from '../components/NavBar';
import BellaBotSlider from '../components/BellaBotSlider';
import { DataContext } from '../components/contexts/DataContextComponent';
import { BELLABOT_BLUE } from '../utils/constants';

const StatsScreen = (): JSX.Element => {
  // The stats page pulls all of its information from the DataContextComponent
  const {
    acceleration,
    deceleration,
    updateAcceleration,
    updateDeceleration,
    lastLinearSpeed,
    lastAngularSpeed,
    instantGyro,
    accumulativeGyro,
    leftWheelError,
    rightWheelError,
    lastBatteryPercent,
    isConnected,
    chargeState
  } = useContext(DataContext);

  return (
    <View id='stats-root' style={styles.container}>
      <NavBar />
      <View style={styles.content}>
        <View style={[styles.column, styles.leftColumn]}>
          <View style={styles.quadrantLarge}>
            <View testID='stat-connected' style={styles.row}><Text style={styles.quadrantText}>CANBus Connected: {String(isConnected).toUpperCase()}</Text></View>
            <View testID='stat-charge-state' style={styles.row}><Text style={styles.quadrantText}>Last Charge State: {chargeState}</Text></View>
            <View testID='stat-battery' style={styles.row}><Text style={styles.quadrantText}>Battery Percent: {lastBatteryPercent}%</Text></View>
          </View>
          <View style={styles.quadrantSmall}>
            <View style={styles.row}>
              <View style={styles.column}><Text style={styles.quadrantText}>Instant Gyro:</Text></View>
              <View testID='stat-instant-gyro-x' style={styles.column}><Text style={styles.quadrantText}>x: {instantGyro.x.toFixed(2)} rad/s</Text></View>
              <View testID='stat-instant-gyro-y' style={styles.column}><Text style={styles.quadrantText}>y: {instantGyro.y.toFixed(2)} rad/s</Text></View>
              <View testID='stat-instant-gyro-z' style={styles.column}><Text style={styles.quadrantText}>z: {instantGyro.z.toFixed(2)} rad/s</Text></View>
            </View>
            <View style={styles.row}>
              <View style={styles.column}><Text style={styles.quadrantText}>Accumulative Gyro:</Text></View>
              <View testID='stat-acc-gyro-x' style={styles.column}><Text style={styles.quadrantText}>x: {accumulativeGyro.x.toFixed(2)} rad/s</Text></View>
              <View testID='stat-acc-gyro-y' style={styles.column}><Text style={styles.quadrantText}>y: {accumulativeGyro.y.toFixed(2)} rad/s</Text></View>
              <View testID='stat-acc-gyro-z' style={styles.column}><Text style={styles.quadrantText}>z: {accumulativeGyro.z.toFixed(2)} rad/s</Text></View>
            </View>
          </View>
        </View>
        <View style={[styles.column, styles.rightColumn]}>
          <View style={styles.quadrantSmall}>
            <View testID='stat-wheel-err-left' style={styles.row}><Text style={styles.quadrantText}>Left Wheel Error: {leftWheelError}</Text></View>
            <View testID='stat-wheel-err-right' style={styles.row}><Text style={styles.quadrantText}>Right Wheel Error: {rightWheelError}</Text></View>
          </View>
          <View style={styles.quadrantLarge}>
            <View style={styles.row}>
              <View testID='stat-lin-speed' style={styles.column}><Text style={styles.quadrantText}>Linear Speed: {lastLinearSpeed.toFixed(2)} m/s</Text></View>
              <View testID='stat-ang-speed' style={styles.column}><Text style={styles.quadrantText}>Angular Speed: {lastAngularSpeed.toFixed(2)} rad/s</Text></View>
            </View>
            <View style={styles.row}>
              <View style={{ flex: 3 }}><Text style={styles.quadrantText}>Acceleration:</Text></View>
              <View style={{ flex: 7 }}>
                <BellaBotSlider
                  min={0}
                  max={1}
                  step={0.1}
                  value={acceleration}
                  onChange={e => updateAcceleration(e)}
                  testID='stat-accel-slider'
                />
              </View>
            </View>
            <View style={styles.row}>
              <View style={{ flex: 3 }}><Text style={styles.quadrantText}>Deceleration:</Text></View>
              <View style={{ flex: 7 }}>
                <BellaBotSlider
                  min={0}
                  max={1}
                  step={0.1}
                  value={deceleration}
                  onChange={e => updateDeceleration(e)}
                  testID='stat-decel-slider'
                />
              </View>
            </View>
          </View>
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
  content: {
    flex: 1,
    flexDirection: 'row',
    backgroundColor: '#fff',
    padding: 16,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 15,
  },
  column: {
    flex: 1,
    justifyContent: 'space-between',
  },
  leftColumn: {
    marginRight: 8,
  },
  rightColumn: {
    marginLeft: 8,
  },
  quadrantSmall: {
    height: '40%',
    backgroundColor: BELLABOT_BLUE,
    justifyContent: 'center',
    paddingLeft: 20,
    marginBottom: 8,
    borderRadius: 15,
  },
  quadrantLarge: {
    height: '55%',
    backgroundColor: BELLABOT_BLUE,
    justifyContent: 'center',
    paddingLeft: 20,
    marginBottom: 8,
    borderRadius: 15,
  },
  row: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
  },
  quadrantText: {
    fontSize: 20,
    color: '#fff',
    fontWeight: "bold",
    fontFamily: "Outfit-Regular"
  },
});

export default StatsScreen;
