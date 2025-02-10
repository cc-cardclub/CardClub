[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![GNU AGPL v3.0][license-shield]][license-url]

# CardClub

A Java implementation of the card game "UNO!"



## About the project

CardClub is a digital replica of the well-known card game UNO. 
The aim of the software is to integrate all cards and rules as in the original game and thus guarantee maximum gaming fun.
In the single player mode it is possible to play against a bot and in the optional multiplayer mode you should be able to play against other players.

### Current state
During the lessons, only a leaderboard was implemented instead of the multiplayer mode.<br>
In order to use it, the CardClubServer must be deployed using Docker. 
Instructions for that can be found [here](https://github.com/cc-cardclub/CardClub#cardclubserver).


### Built with

The whole GUI is built with [JavaFX](https://openjfx.io/) and SceneBuilder to get a wide platform support.


## Getting started
### CardClubClient
Download the latest release [here](https://github.com/cc-cardclub/CardClub/releases).
 * Windows: Execute the EXE or the JAR-File
 * Other: Execute the JAR-File (OpenJDK 17.0.1 & JavaFX 17.0.2 required)
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



Raven Burkard:

- GitHub: [Mathematti](https://github.com/Mathematti)
- Email: [Raven Burkard](mailto:raven.burkard@gmail.com)



Lisa-Marie Hörmann:

- GitHub: [dasechtelima](https://github.com/dasechtelima)
- Email: [Lisa-Marie Hörmann](mailto:lisa-marie.hoermann@htl.rennweg.at)



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/cc-cardclub/CardClub?style=for-the-badge
[contributors-url]: https://github.com/cc-cardclub/CardClub/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/cc-cardclub/CardClub?style=for-the-badge
[forks-url]: https://github.com/cc-cardclub/CardClub/network/members
[stars-shield]: https://img.shields.io/github/stars/cc-cardclub/CardClub?style=for-the-badge
[stars-url]: https://github.com/cc-cardclub/CardClub/stargazers
[issues-shield]: https://img.shields.io/github/issues/cc-cardclub/CardClub?style=for-the-badge
[issues-url]: https://github.com/cc-cardclub/CardClub/issues
[license-shield]: https://img.shields.io/github/license/cc-cardclub/CardClub?style=for-the-badge
[license-url]: https://github.com/cc-cardclub/CardClub/blob/master/LICENSE