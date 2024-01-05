# Use the official Node.js 21.1.0 image as a base image
FROM node:21.1.0-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire application to the working directory
COPY . .

# Build the React app
RUN npm run build

# Expose the port that your React app is running on (usually 3000)
EXPOSE 3000

# Specify the command to run your React app
CMD ["npm", "start"]
