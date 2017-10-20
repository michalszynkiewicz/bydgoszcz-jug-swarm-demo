# Materials from WildFly Swarm presentation at Bydgoszcz JUG

Click [here](https://github.com/michalszynkiewicz/bydgoszcz-jug-swarm-demo/blob/master/swarm-presentation-bjug.pdf) to view the slides

## Prerequisites
The document assumes you have 
 - a unix shell
 - httpie installed, to install it on fedora run `sudo dnf install httpie`

## Running the example

### Installation

Take a look at the tags in this repository for various
steps of this project's evolution.

To run it on minishift:
 - start  minishift 
    - [download it](https://github.com/minishift/minishift/releases)
    - start it with 
        ```
        minishift start
        ```
    - log in:
        ```
        oc login $(minishift ip):8443 -u developer -p developer
        ```
    - create a new project named `bjug`:
        ```
        oc new-project bjug
        ```
 - install Zipkin:
    ```
    git clone https://github.com/michalszynkiewicz/zipkin-openshift
    cd zipkin-openshift
    ./install.sh
    ```
 - install Keycloak:
    ```
    git clone https://github.com/michalszynkiewicz/keycloak-openshift
    cd zipkin-openshift
    ./install.sh
    ```
 - install the nutrition-service:
    ```
    git clone https://github.com/michalszynkiewicz/nutrition-service
    ./runOpenshift.sh
    ```
 - install this project on OpenShift:
    ```
    mvn fabric8:deploy -Popenshift -DskipTests
    ```
    
    
### Playing with it

Checking if application is alive:
```
http GET apples-bjug.192.168.42.11.nip.io/alive
```

Getting apples:
```
http GET apples-bjug.192.168.42.11.nip.io/apples
```

Adding apples
```
token=$(./getToken.sh keycloak-bjug.$(minishift ip).nip.io)
http POST http://apples-bjug.192.168.42.216.nip.io/apples name=lobo Authorization:"Bearer $token"
```

#### Accessing Zipkin
To see what has been collected on Zipkin:
- check your minishift ip:
```
minishift ip
``` 
- open your browser and go to 
http://zipkin-bjug.your-minishift-ip.nip.io
