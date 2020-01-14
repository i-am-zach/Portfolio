import React, { useState, useEffect } from "react";
import { StyleSheet, View, ScrollView } from "react-native";
import CircularProgress from "./components/CircularProgress";
import RoundButton from "./components/RoundButton";
import fwTenMin from "./utils/exerciseFactory";
import ExerciseComponent from "./components/ExerciseComponent";
import PrimaryExerciseComponent from "./components/PrimaryExerciseComponent";

const App = duration => {
  const [seconds, setSeconds] = useState(0);
  const [starts, setStarts] = useState([]);
  const [stops, setStops] = useState([]);
  const [isActive, setIsActive] = useState(false);
  const [currIdx, setCurrIdx] = useState(0);
  const [exercises, setExercises] = useState(fwTenMin);

  const calculateTime = () => {
    let time = 0;
    for (let index = 0; index < starts.length; index++) {
      if (index < stops.length) {
        time += stops[index].time - starts[index].time;
      } else {
        time += new Date().getTime() - starts[index].time;
      }
    }
    return time / 1000;
  };

  function toggle() {
    isActive
      ? setStops(stops => {
          return [
            ...stops,
            { time: new Date().getTime(), index: stops.length }
          ];
        })
      : setStarts(starts => {
          return [
            ...starts,
            { time: new Date().getTime(), index: starts.length }
          ];
        });
    setIsActive(!isActive);
  }

  function reset() {
    setSeconds(0);
    setIsActive(false);
    setStarts([]);
    setStops([]);
  }

  function nextExercise() {
    if (calculateTime() > exercises[currIdx].duration) {
      setSeconds(seconds => 0);
      setStarts(starts => [
        { time: 0, index: 0 },
        { time: new Date().getTime(), index: 1 }
      ]);
      setStops(stops => [{ time: 0, index: 0 }]);
      setTimeout(() => setCurrIdx(idx => currIdx + 1 < exercises.length ? currIdx + 1 : 0), 333);
    }
  }

  useEffect(() => {
    let interval = null;
    if (isActive) {
      interval = setInterval(() => {
        setSeconds(seconds => calculateTime());
        if (seconds > exercises[currIdx].duration) {
          nextExercise();
        }
      }, 100);
    } else if (!isActive && seconds !== 0) {
      clearInterval(interval);
    }
    return () => clearInterval(interval);
  }, [isActive, seconds]);

  const changeExercise = index => {
    setCurrIdx(index);
    reset();
  }

  return (
    <View style={styles.container}>
      <ScrollView horizontal={true}>
        <View style={styles.exWrapper}>
          <PrimaryExerciseComponent exercise={exercises[currIdx]} />
          {exercises.map(ex => {
            if(ex.id !== currIdx) {
              return <ExerciseComponent key={ex.id} exercise={ex} onPress={changeExercise.bind(this, ex.id)}/>
            }
          })}
        </View>
      </ScrollView>
      <View style={styles.svgContainer}>
        <CircularProgress
          progress={exercises[currIdx].duration - seconds}
          duration={exercises[currIdx].duration}
        />
        <View style={styles.buttonWrapper}>
          <RoundButton title={isActive ? "Pause" : "Start"} onPress={toggle} />
          <RoundButton title="Reset" onPress={reset} />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    paddingTop: 50,
    backgroundColor: "#fff9e8",
    paddingBottom: 200
  },
  svgContainer: {
    alignItems: "center",
    backgroundColor: "#fff9e8"
  },
  exWrapper: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 25
  },
  buttonWrapper: {
    flexDirection: "row",
    alignSelf: "stretch",
    justifyContent: "space-around"
  },
  timerText: {
    color: "black",
    fontSize: 50
  }
});

export default App;
