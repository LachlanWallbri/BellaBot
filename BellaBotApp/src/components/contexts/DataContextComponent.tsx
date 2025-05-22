import React from "react";
import { createContext, useContext, useEffect, useState } from "react";
import { NativeModules } from "react-native";
import { DataContextType, gyroDataType } from "../../utils/types";
import { POLLING_RATE } from "../../utils/constants";
import { ScreenContext } from "./ScreenContextComponent";
const { CANBusModule } = NativeModules;

export const DataContext = createContext<DataContextType | undefined>(undefined);



const DataContextComponent = ({ children }): JSX.Element => {
  const { currentScreen } = useContext(ScreenContext);

  const [lastBatteryPercent, setlastBatteryPercent] = useState<number>(0);
  const [lastLinearSpeed, setlastLinearSpeed] = useState<number>(0);
  const [lastAngularSpeed, setlastAngularSpeed] = useState<number>(0);
  const [instantGyro, setInstantGyro] = useState<gyroDataType>({ x: 0, y: 0, z: 0 });
  const [accumulativeGyro, setAccumulativeGyro] = useState<gyroDataType>({ x: 0, y: 0, z: 0 });
  const [leftWheelError, setLeftWheelError] = useState<string>("None");
  const [rightWheelError, setRightWheelError] = useState<string>("None");
  const [isConnected, setIsConnected] = useState<boolean>(false);
  const [chargeState, setChargeState] = useState<string>("Disconnected");

  const [acceleration, setAcceleration] = useState<number>(0.6);
  const [deceleration, setDeceleration] = useState<number>(0.6);

  const updateAcceleration = async (newAcceleration: number) => {
    try {
      setAcceleration(newAcceleration);
      await CANBusModule.setAcceleration(newAcceleration);
    } catch (e) {
      console.log("ACCELERATION Error: " + e)
    }
  }

  const updateDeceleration = async (newDeceleration: number) => {
    try {
      setDeceleration(newDeceleration);
      await CANBusModule.setDeceleration(newDeceleration);
    } catch (e) {
      console.log("DECELERATION Error: " + e);
    }
  }

  // The Use Effect that fetches data from the CANBus.
  // Only runs for the STATS page currently due to performance issues
  useEffect(() => {
    const interval = setInterval(async () => {
      try {
        if (currentScreen === "STATS") {
          const chargeStateRes: string = await CANBusModule.getLastChargeState() ?? "Disconnected";
          setChargeState(chargeStateRes);

          const instGyroRes = await CANBusModule.getInstantGyroscope();
          setInstantGyro(instGyroRes);

          const accGyroRes = await CANBusModule.getAccumulativeGyroscope();
          setAccumulativeGyro(accGyroRes);

          let leftErrorRes = await CANBusModule.getLeftWheelError();
          if (!leftErrorRes) { leftErrorRes = "None" }
          setLeftWheelError(leftErrorRes);

          let rightErrorRes = await CANBusModule.getRightWheelError();
          if (!rightErrorRes) { rightErrorRes = "None" }
          setRightWheelError(rightErrorRes);

          const busConnRes = await CANBusModule.getIsCanBusConnected();
          setIsConnected(busConnRes);

          const batteryPercentRes: number = await CANBusModule.getBatteryPercent() ?? 100;
          setlastBatteryPercent(parseFloat(batteryPercentRes.toFixed(1)));

          const linSpeedRes: number = await CANBusModule.getLinearSpeed() ?? 0;
          setlastLinearSpeed(parseFloat(linSpeedRes.toFixed(1)));

          const angSpeedRes: number = await CANBusModule.getAngularSpeed() ?? 0;
          setlastAngularSpeed(parseFloat(angSpeedRes.toFixed(1)));

        }
      } catch (error) {
        console.error('One of the calls to the api failed: ', error);
      }
    }, POLLING_RATE);

    return () => clearInterval(interval);
  }, [currentScreen]);


  return (
    <DataContext.Provider
      value={{
        acceleration,
        deceleration,
        updateAcceleration,
        updateDeceleration,
        lastBatteryPercent,
        lastLinearSpeed,
        lastAngularSpeed,
        instantGyro,
        accumulativeGyro,
        leftWheelError,
        rightWheelError,
        isConnected,
        chargeState,
      }}>
      {children}
    </DataContext.Provider>);
};

export default DataContextComponent;