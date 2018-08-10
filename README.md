# project-management
This pproject been implemented using below technologies
* Java 8
* Junit
* MongoDB
* Docker
# Follow the below steps(Commands) to start the application
1. Create a docker network to connect micro service with Mongo container
   <br/> 'docker network create project-network'
2. Star the mongo container
    <br/> 'docker run -d --name mongocontainer --network=project-network -v ~/mongo-data:/data/db mongo'
3. Build the docker image
    <br/> 'docker build -t project-management-image:latest .'
4. Launch the docker application
    <br/> 'docker run -p 8080:8080 --name project-management-container --network project-network project-management-image'
