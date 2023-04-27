This repo shows how to use config server to setup configurations globally. You can either use `http://localhost:8888/limits-service/default` or `http://localhost:8080/limits` to access the limits service. The config server redirects request with url `http://localhost:8888/limits-service/default` to `http://localhost:8080/limits`. 

You need to use `git init` in directory `git-local-config-repo` before running config server, since config server requires a git repo for configurations. 
