import React, { useState } from "react";
import { StyleSheet, View, Text, TouchableOpacity } from "react-native";

const GoalItem = (props) => {
  return (
    <TouchableOpacity
      activeOpacity={0.8}
      onPress={props.onDelete.bind(this, props.id)}
    >
      <View style={styles.itemGoal}>
        <Text style={styles.titleS}> {props.title} </Text>
        <Text style={styles.goalsS}> {props.goal} </Text>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  itemGoal: {
    padding: 10,
    marginVertical: 10,
    backgroundColor: "#ccc",
    borderColor: "black",
    borderWidth: 1.5,
  },
  titleS: {
    fontSize: 25,
    textAlign: "center",
    fontStyle: "italic",
    color: "red",
  },
  goalsS: {
    fontSize: 19,
  },
});

export default GoalItem;
