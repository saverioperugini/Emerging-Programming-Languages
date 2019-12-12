# Smash Search

This is a Super Smash Brothers tournament tracker which utilizes the [Fable](https://fable.io/)
F# compiler, the [Elmish](https://elmish.github.io/elmish/) architecture,
[React](https://reactjs.org/), and the [smash.gg](https://developer.smash.gg/docs/intro) API.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

[.NET Core SDK 3.0.0](https://dotnet.microsoft.com/download) or greater

[Node.js](https://nodejs.org/en/)

[Python 3.7.0](https://www.python.org/downloads/) or greater

Pip

```
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
python get-pip.py
```

[Yarn](https://yarnpkg.com/en/)

Paket

```
dotnet tool install --global Paket
```


### Installing

Install the Python GraphQL Client package

```
pip install graphqlclient
```

Run paket install in the root directory of SmashSearch

```
paket install
```

Run yarn

```
yarn
```

## Running

To host the system locally, execute the ```run.cmd``` file with the following parameters:

```
<path_to_root_>/run.cmd <max_number_of_tournaments> <latitude,longitude> <max_distance>
```

Then navigate to localhost:8080

## Examples

Fetching 20 tournaments with a maximum distance of 100 miles from Dayton, OH

```
run.cmd 20 "39.7420426,-84.1845668" 100mi
```

Fetching 100 tournaments with a maximum distance of 50 miles from San Francisco, CA

```
run.cmd 100 "37.779379,-122.418433" 50mi
```

## Authors

* **Tyler Berkshire** - [GitHub Profile](https://github.com/BerkshireT)

## Acknowledgments

* [Dr. Saverio Perugini](http://academic.udayton.edu/SaverioPerugini/)
* [JetBrainsTV](https://www.youtube.com/user/JetBrainsTV)