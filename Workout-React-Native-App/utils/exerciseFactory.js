const thirtySecWorkout = (name, idx) => ({name, duration: 30, description: '', id: idx});

const exerciseDb =  {
  "durations": {
    "30s": {
      "duration": 30,
      "names": [
        "Scissor Kicks",
        "Lying Leg Raise",
        "Feet Crossovers",
        "Reverse Crunch",
        "L Sit Toe Touches",
        "Rest, Stretch, and Catch Breath",
        "Plank Knee Ins",
        "Spiderman Push Ups",
        "Side Plank Raises",
        "Swap Sides",
        "Mountain Climbers",
        "Ab Contractions",
        "Oblique Leg Raises",
        "Bodyweight Crunches",
        "V Sit Static Hold",
        "Plank Static Hold",
        "Extended Plank",
        "Russian Twists",
        "Situp Cross Punches",
        "Leg Raise Hold"
      ]
    }
  }
}

const fwTenMin = [
  "Scissor Kicks",
  "Lying Leg Raise",
  "Feet Crossovers",
  "Reverse Crunch",
  "L Sit Toe Touches",
  "Rest, Stretch, and Catch Breath",
  "Plank Knee Ins",
  "Spiderman Push Ups",
  "Side Plank Raises",
  "Swap Sides",
  "Mountain Climbers",
  "Ab Contractions",
  "Oblique Leg Raises",
  "Bodyweight Crunches",
  "V Sit Static Hold",
  "Plank Static Hold",
  "Extended Plank",
  "Russian Twists",
  "Situp Cross Punches",
  "Leg Raise Hold"
].map((ex, index) => thirtySecWorkout(ex, index));

let exercises = [];
for(let i = 0; i < exerciseDb['durations']['30s']['names'].length; i++){
  exercises.push(thirtySecWorkout(exerciseDb['durations']['30s']['names'][i], i));
}

export default fwTenMin;