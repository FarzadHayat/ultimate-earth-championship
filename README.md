# Ultimate Earth Championship

A strategic turn-based player-vs-computer game built with Java and Swing.

Aliens have invaded Earth and resurrected humanity's greatest champions to battle each other in an intergalactic sports tournament. Assemble a team of up to 9 champions, equip them with weapons, and fight through a bracket of AI-controlled teams in a checkered battle arena. Enemy teams intelligently fight each other and make purchases from the market between rounds.

**SENG201 project (2023-S1)** — Farzad Hayatbakhsh & Oliver Coates

---

## Requirements

- Java 17 or later ([Download JRE](https://www.oracle.com/java/technologies/downloads/))
- Windows (game is designed for Windows)

## Quick Start

1. Download `fha62_oco36_SportsTournament.jar` from this repository.
2. Open a terminal in the same folder and run:

```bash
java -jar fha62_oco36_SportsTournament.jar
```

## Project Structure

| Path | Contents |
|------|----------|
| `UltimateEarthChampionship/` | Eclipse project source code and unit tests |
| `doc/` | Compiled Javadoc documentation |
| `uml/` | UML use case, class, and state diagrams (PNG) |
| `Report.pdf` | Project report |
| `fha62_oco36_SportsTournament.jar` | Runnable JAR |

## Importing into Eclipse

1. Open Eclipse with JDK 17+ installed. Verify with `java -version`.
2. Go to **File → Open Projects from File System**.
3. Click **Directory** next to "Import source" and select the `UltimateEarthChampionship/` folder.
4. Ensure `UltimateEarthChampionship` is checked, then click **Finish**.

The project will appear in your Package Explorer.

## Running in Eclipse

1. Expand `src → manager`.
2. Right-click `GraphicalGameManager.java` and select **Run As → Java Application**.

## Running Unit Tests

Right-click the `tests` package and select **Coverage As → JUnit Test**.

## Tech Stack

- **Language**: Java 17
- **GUI**: Swing
- **IDE**: Eclipse
- **Testing**: JUnit
- **Design**: UML (use case, class, state diagrams)

## Credits

- Champion images — [ImgCreator.AI](https://imgcreator.zmo.ai/)
- Background and weapon images — [Bing AI Image Creator](https://www.bing.com/create)
- Icon images by Ravenmore — [Fantasy Icon Pack on itch.io](https://ravenmore.itch.io/fantasy-icon-pack)
