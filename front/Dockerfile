FROM node:14.15.3-alpine AS build

WORKDIR /app
COPY package*.json ./
RUN NODE_ENV=dev npm install
COPY . /app
RUN npm run build

# Make final image
FROM nginx:latest AS ngi
# Copying compiled code and nginx config to different folder
# NOTE: This path may change according to your project's output folder
ENV API_URL="http://localhost:8080"

COPY --from=build /app/dist/grenoble-hands-on /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/templates/default.conf.template

# Exposing a port, here it means that inside the container
# the app will be using Port 80 while running
EXPOSE 80
