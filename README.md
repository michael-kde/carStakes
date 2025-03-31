Very simple Spring boot appl.,betting for cars.
4 cars are participating:Ferrari, BMW, Audi, Honda.

1)To get a stake 
a)for a certain car run in browser (default port localhost:8080/): "localhost:8080/carstakes/getstake/{car}"
b)for all cars run: "localhost:8080/carstakes/getstake"

2)To add a stake for a certain car run:
localhost:8080/carstakes/addstake/{car}/{stake}

* Simple error response with status 200 for all cases.
