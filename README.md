# FNR Utils

Et Kotlin/Java-bibliotek for generering, validering og manipulering av norske fødselsnumre.

## Funksjoner

- ✅ **Validering** av norske fødselsnumre
- 🎲 **Generering** av tilfeldige gyldige fødselsnumre
- 🔄 **Konvertering** mellom reelle og syntetiske fødselsnumre
- 📊 **Utvinning** av informasjon (alder, kjønn, fødselsdato)
- 🎨 **Formatering** av fødselsnumre og datoer

## Installasjon

### Maven
```xml
<dependency>
    <groupId>io.github.guerillaqc</groupId>
    <artifactId>fnr-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'io.github.guerillaqc:fnr-utils:1.0.0'
```

## Quick Start

```kotlin
import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.FnrValidator
import io.github.guerillaqc.fnr.utils.FnrUtils

// Generer tilfeldig fødselsnummer
val fnr = FnrGenerator.getRandomPersonFnr()
println(fnr) // "12345678901"

// Valider fødselsnummer
val erGyldig = FnrValidator.erGyldig(fnr)
println("Gyldig: $erGyldig") // true

// Hent informasjon
val alder = FnrUtils.alderFraFodselsnummer(fnr)
val kjonn = FnrUtils.kjonnFraFodselsnummer(fnr)
val fdato = FnrUtils.fodselsdatoFraFodselsnummer(fnr)

println("Alder: $alder, Kjønn: $kjonn, Født: $fdato")
```

## API-oversikt

### FnrGenerator
Generer tilfeldige gyldige fødselsnumre med spesifikke kriterier:

```kotlin
// Grunnleggende generering
val fnr = FnrGenerator.getRandomPersonFnr()
val fdato = FnrGenerator.getRandomPersonFdato()

// Generering basert på kjønn
val mann = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.MANN)
val kvinne = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)

// Generering basert på alder
val barn = FnrGenerator.getRandomFnrBarn()          // 1-17 år
val barnehage = FnrGenerator.getRandomFnrBarnehage() // 1-5 år  
val sfo = FnrGenerator.getRandomFnrSFO()            // 6-9 år
val millenial = FnrGenerator.getRandomFnrMillenial() // 0-25 år

// Generering med spesifikk alder
val person40 = FnrGenerator.getRandomFnrAlder(40)

// Generering av forelder til eksisterende person
val forelder = FnrGenerator.getRandomFnrForelderTilFnr("12345678901")
```

### FnrValidator
Valider fødselsnumre:

```kotlin
val erGyldig = FnrValidator.erGyldig("12345678901")
val erGyldigSyntetisk = FnrValidator.erGyldigSyntetisk("12845678901")
```

### FnrUtils
Trekk ut informasjon og manipuler fødselsnumre:

```kotlin
// Informasjonsutvinning
val alder = FnrUtils.alderFraFodselsnummer("12345678901")
val kjonn = FnrUtils.kjonnFraFodselsnummer("12345678901")
val fdato = FnrUtils.fodselsdatoFraFodselsnummer("12345678901")

// Formatering
val formatertFnr = FnrUtils.formaterFodselsnummer("12345678901", "-")
// "123456-78901"

val formatertDato = FnrUtils.formaterFodselsdato("12121985", "/")
// "12/12/1985"

// Konvertering mellom reelle og syntetiske numre
val syntetisk = FnrUtils.konverterTilSyntetisk("12345678901")
val reell = FnrUtils.konverterFraSyntetisk("12845678901")

// Alderberegning fra dato
val alderFraDato = FnrUtils.alderFraFodselsdato("12121985")
val alderFraKomponenter = FnrUtils.alderFraFodselsdato(12, 12, 1985)
```

## Enums

### Kjonn
```kotlin
enum class Kjonn {
    MANN,
    KVINNE
}
```

## Syntetiske fødselsnumre

Biblioteket støtter både reelle og syntetiske fødselsnumre. Syntetiske numre har måned 80-91 (januar=80, desember=91) og brukes for testing og demøformål.

```kotlin
// Konverter reelt til syntetisk
val reellt = "12125678901"
val syntetisk = FnrUtils.konverterTilSyntetisk(reellt)
// "12925678901" (måned endret fra 12 til 92)

// Konverter syntetisk til reelt
val tilbake = FnrUtils.konverterFraSyntetisk(syntetisk)
// "12125678901" (med nye kontrollsifre)
```

## Testing

Biblioteket inkluderer omfattende tester med Kotest:

```kotlin
"Generert tilfeldig fnr er gyldig" {
    repeat(100) {
        val fnr = FnrGenerator.getRandomPersonFnr()
        FnrValidator.erGyldig(fnr) shouldBe true
    }
}
```

## Krav

- Kotlin 1.8+
- Java 11+

## Bidrag

Bidrag er velkommen! Se [CONTRIBUTING.md](CONTRIBUTING.md) for retningslinjer.

## Lisens

Dette prosjektet er lisensiert under MIT-lisensen - se [LICENSE](LICENSE) filen for detaljer.

## Ansvarsfraskrivelse

Dette biblioteket er laget for utvikling og testing. Ved bruk i produksjon, sørg for å følge gjeldende lover og regler for behandling av personnumre i Norge.