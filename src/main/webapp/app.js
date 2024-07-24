const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public'));

// Dummy data (replace with a database in a real-world application)
const users = [];

// Home page
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

// Registration page
app.get('/register', (req, res) => {
  res.sendFile(__dirname + '/register.html');
});

// Handle registration form submission
app.post('/register', (req, res) => {
  const { username, password } = req.body;
  // Basic validation - check if username already exists
  if (users.find(user => user.username === username)) {
    return res.send('Username already exists. Please choose another.');
  }

  // Store user data (in-memory for simplicity, use a database in production)
  users.push({ username, password });

  res.send('Registration successful! <a href="/">Go to login</a>');
});

// Login page
app.get('/login', (req, res) => {
  res.sendFile(__dirname + '/login.html');
});

// Handle login form submission
app.post('/login', (req, res) => {
  const { username, password } = req.body;
  const user = users.find(user => user.username === username && user.password === password);

  if (user) {
    res.send(`Welcome, ${username}!`);
  } else {
    res.send('Invalid username or password. <a href="/login">Try again</a>');
  }
});


// Theatrebooking page
app.get('/theatreSelection', (req, res) => {
  res.sendFile(__dirname + '/theatreSelection.html');
});

// Booking page
app.get('/booking', (req, res) => {
  res.sendFile(__dirname + '/booking.html');
});


// Seat Selection page
app.get('/seatSelection', (req, res) => {
  res.sendFile(__dirname + '/seatSelection.html');
});

// Payment page
app.get('/payment', (req, res) => {
  res.sendFile(__dirname + '/payment.html');
});

app.listen(port, () => {
  console.log(`Server is listening at http://localhost:${port}`);
});
