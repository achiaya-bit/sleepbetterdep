# Frontend Dockerfile (dev)
FROM node:18

WORKDIR /app

# copy package files then install to leverage cache
COPY package*.json ./
RUN npm ci

# copy everything
COPY . .

# expose the vite dev server port
EXPOSE 5173

# run vite dev and bind to 0.0.0.0
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
