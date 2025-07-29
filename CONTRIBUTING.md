# Bidragsguide

Takk for at du vurderer å bidra til FNR Utils! Vi setter pris på alle bidrag, fra feilrapporter til nye funksjoner.

## Hvordan bidra

### 🐛 Rapporter bugs

Før du rapporterer en bug:
- Sjekk at problemet ikke allerede er rapportert i [Issues](https://github.com/guerillaqc/fnr-utils/issues)
- Test med siste versjon av biblioteket

**Bug-rapport bør inneholde:**
- Kort beskrivelse av problemet
- Steg for å reprodusere
- Forventet vs faktisk oppførsel
- Kotlin/Java-versjon og miljø
- Minimal kodeeksempel som reproduserer feilen

```kotlin
// Eksempel på kode som ikke fungerer som forventet
val fnr = "12345678901"
val result = FnrUtils.alderFraFodselsnummer(fnr)
// Forventet: 25, Faktisk: 125
```

### 💡 Foreslå nye funksjoner

Vi ønsker forslag til nye funksjoner! Opprett en issue med:
- Beskrivelse av funksjonen
- Bruksscenario - hvorfor trengs det?
- Forslag til API-design
- Eksempler på bruk

### 🔧 Kodeendringer

1. **Fork** repositoryet
2. **Opprett en branch** for din endring:
   ```bash
   git checkout -b feature/ny-funksjon
   # eller
   git checkout -b fix/bug-beskrivelse
   ```
3. **Gjør endringene** dine
4. **Legg til tester** for nye funksjoner
5. **Kjør testene** lokalt
6. **Commit** med tydelig melding
7. **Push** til din fork
8. **Opprett en Pull Request**

## Utviklingsmiljø

### Forutsetninger
- Java 11+
- Kotlin 1.8+
- Git

### Oppsett
```bash
git clone https://github.com/guerillaqc/fnr-utils.git
cd fnr-utils
./gradlew build
```

### Kjør tester
```bash
# Alle tester
./gradlew test

# Spesifikk test
./gradlew test --tests "FnrGeneratorTest"

# Med coverage
./gradlew test jacocoTestReport
```

## Kodestandarder

### Kotlin Style Guide
Vi følger [Kotlins offisielle stil guide](https://kotlinlang.org/docs/coding-conventions.html):

- **Navn**: camelCase for funksjoner og variabler, PascalCase for klasser
- **Innrykk**: 4 spaces, ikke tabs
- **Linjelengde**: Maksimalt 120 tegn
- **Kommentarer**: Norsk eller engelsk, være konsekvent innen samme fil

### Kodeeksempel
```kotlin
/**
 * Genererer tilfeldig fødselsnummer for spesifisert kjønn.
 * 
 * @param kjonn ønsket kjønn for generert person
 * @return gyldig 11-sifret fødselsnummer
 */
fun getRandomPersonFnrKjonn(kjonn: Enums.Kjonn): String {
    return createRandomFnr(kjonn = kjonn)
}
```

### Dokumentasjon
- **Alle offentlige funksjoner** må ha KDoc/Javadoc
- **Kompleks logikk** skal kommenteres
- **README** må oppdateres for nye funksjoner
- **Eksempler** i dokumentasjon skal testes

### Testing
- **Nye funksjoner** må ha tester
- **Bruk Kotest** for konsistens
- **Test edge cases** og feilscenarier
- **Minimum 80% coverage** for nye kode

```kotlin
class FnrUtilsTest : StringSpec({
    "alderFraFodselsnummer skal returnere korrekt alder" {
        val fnr = "12345678901"
        val alder = FnrUtils.alderFraFodselsnummer(fnr)
        alder shouldBeGreaterThan 0
        alder shouldBeLessThan 150
    }
    
    "alderFraFodselsnummer skal kaste exception for ugyldig fnr" {
        shouldThrow<IllegalArgumentException> {
            FnrUtils.alderFraFodselsnummer("invalid")
        }
    }
})
```

## Pull Request Guidelines

### Før du sender PR
- [ ] Koden bygger uten feil (`./gradlew build`)
- [ ] Alle tester passerer (`./gradlew test`)
- [ ] Nye funksjoner har tester
- [ ] Dokumentasjon er oppdatert
- [ ] Commits har tydelige meldinger

### PR-beskrivelse
```markdown
## Hva endres
Kort beskrivelse av endringen

## Hvorfor
Forklaring av problemet som løses eller funksjonen som legges til

## Testing
- [ ] Nye tester lagt til
- [ ] Eksisterende tester passerer
- [ ] Manuelt testet scenario X, Y, Z

## Checklist
- [ ] Dokumentasjon oppdatert
- [ ] Breaking changes dokumentert
- [ ] Backwards kompatibilitet vurdert
```

### Commit Messages
Bruk tydelige commit-meldinger:
```bash
# Bra
git commit -m "Add support for synthetic FNR validation"
git commit -m "Fix age calculation for people born in 1900s"
git commit -m "Update README with new API examples"

# Ikke så bra  
git commit -m "fix bug"
git commit -m "changes"
git commit -m "wip"
```

## Spørsmål og hjelp

- **Generelle spørsmål**: Opprett en [Discussion](https://github.com/guerillaqc/fnr-utils/discussions)
- **Bugs eller feature requests**: Opprett en [Issue](https://github.com/guerillaqc/fnr-utils/issues)
- **Sikkerhetsproblemer**: Send epost til [security@guerillaqc.io](mailto:security@guerillaqc.io)

## Oppførselskode

Vi forventer at alle bidragsytere følger våre grunnleggende prinsipper:
- **Vær respektfull** overfor andre bidragsytere
- **Vær konstruktiv** i tilbakemeldinger
- **Fokuser på koden**, ikke personen
- **Hjelp nye bidragsytere** å komme i gang

## Lisens

Ved å bidra til dette prosjektet godtar du at dine bidrag vil bli lisensiert under samme lisens som prosjektet (MIT).

---

Tusen takk for ditt bidrag! 🎉