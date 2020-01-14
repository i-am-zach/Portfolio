import React from "react";
import { View, Text, StyleSheet } from "react-native";

const PrimaryExerciseComponent = props => {
  const exercise = props.exercise;

  return (
    <View style={styles.wrapper}>
      <View style={styles.innerBoarder}>
        <Text style={styles.text}>{exercise.name}</Text>
        <Text style={styles.text}>{exercise.duration}s</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
    wrapper: {
      width: 200,
      height: 133,
      borderRadius: 30,
      backgroundColor: "#ff6969",
      alignItems: "center",
      justifyContent: "center",
      marginHorizontal: 5,
    },
    text: {
        color: '#fff9e8',
        fontSize: 24,
    },
    innerBoarder: {
        width: 195,
        height: 130,
        borderRadius: 28,
        borderColor: '#fff9e8',
        borderWidth: 3,
        backgroundColor: 'transparent',
        alignItems: 'center',
        justifyContent: 'center',
    }
  });

export default PrimaryExerciseComponent;
