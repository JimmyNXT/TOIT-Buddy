# TIOT Buddy
TIOT Buddy exposes the TOIT GRPC Api as a simple RestApi.

## Usage
TOIT Buddy runs in a docker container and also uses a docker container to compile the code.
 1. Copy the .env.example in the root directory and rename the copy to .env
 2. Enter your username (Email) add password into the .env file in the fields provided
 3. Run the run. sh file
    - On Windows launch git bash and enter the command ```sh run.sh```
    - On Linux launch the terminal and run ```./run.sh```

### Accessing the api
Swagger ui is bundled with the platform. You can navigate to ```http://[Docker Server IP]/Swagger-ui.htlm``` to access the Swagger ui.
All data is returned as JSON objects and the path to each endpoint is in the swagger ui.
These paths can be accessed through HTTP requests by prepending ```http://[Docker Server IP]``` to the displayed path.