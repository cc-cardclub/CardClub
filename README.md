# CardClub

A Java implementation of the card game "UNO!"



## About the project

CardClub is a digital replica of the well-known card game UNO. 
The aim of the software is to integrate all cards and rules as in the original game and thus guarantee maximum gaming fun.
In the single player mode it is possible to play against a bot and in the optional multiplayer mode you should be able to play against other players.

### Current state
During the lessons, only a leaderboard was implemented instead of the multiplayer mode.<br>
In order to use it, the CardClubServer must be deployed using Docker. 
Instructions for this can be found [here](https://github.com/cc-cardclub/CardClub#cardclubserver).


### Built with

The whole GUI is built with [JavaFX](https://openjfx.io/) and SceneBuilder to get a wide platform support.


## Getting started
### CardClubClient
Download the latest release [here](https://github.com/cc-cardclub/CardClub/releases).
 * Windows: Execute the EXE or the JAR-File
 * Other: Execute the JAR-File
### CardClubServer
The CardClubServer is built using Docker.

1. Clone the repo 

   ```sh
   git clone https://github.com/cc-cardclub/CardClub.git
   ```
2. Change the passwords in .env

   ```sh
   cd CardClub
   nano .env
   ```
3. Make start.sh executable

   ```sh
   chmod +x start.sh
   ```
4. Run start.sh to set up the server containers

   ```sh
   ./start.sh
   ```

## Contact

Bernd Reither:

- GitHub: [sidious38](https://github.com/sidious38)
- Email: [Bernd Reither](mailto:bernd.reither@htl.rennweg.at)



Mattias Burkard:

- GitHub: [Mathematti](https://github.com/Mathematti)
- Email: [Mattias Burkard](mailto:mattias.burkard@htl.rennweg.at)



Lisa-Marie Hörmann:

- GitHub: [dasechtelima](https://github.com/dasechtelima)
- Email: [Lisa-Marie Hörmann](mailto:lisa-marie.hoermann@htl.rennweg.at)
