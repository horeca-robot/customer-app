# Choose the Image which has Node installed already
FROM node:lts-alpine


# install simple http server for serving static content
RUN npm install -g http-server

# set working directory
WORKDIR /customerapp-vue


COPY package*.json ./


# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

EXPOSE 8080
CMD [ "http-server -a localhost -p 8081", "dist" ]