import React from "react";
import { View, Text, StyleSheet, TouchableOpacity } from "react-native";

const ExerciseComponent = props => {
  const exercise = props.exercise;
  const onPress = props.onPress;

  const styles = StyleSheet.create({
    wrapper: {
      width: 150,
      height: 100,
      borderRadius: 30,
      backgroundColor: "#EBE5D5",
      alignItems: "center",
      justifyContent: "center",
      marginHorizontal: 5
    },
    textWrapper: {
      width: "100%",
      alignItems: "center",
      justifyContent: "center"
    },
    text: {
      color: "#ff6969",
      fontWeight: "300"
    },
    innerBoarder: {
      width: 145,
      height: 94,
      borderRadius: 28,
      borderColor: "#fff9e8",
      borderWidth: 3,
      backgroundColor: "transparent",
      alignItems: "center",
      justifyContent: "center"
    }
  });

  return (
    <TouchableOpacity activeOpacity={0.7} onPress={onPress.bind(this, exercise.id)}>
      <View style={styles.wrapper}>
        <View style={styles.innerBoarder}>
          <View style={styles.textWrapper}>
            <Text style={styles.text}>{exercise.name}</Text>
          </View>
          <Text style={styles.text}>{exercise.duration}s</Text>
        </View>
      </View>
    </TouchableOpacity>
  );
};

export default ExerciseComponent;
