# Fix Build Error: No matching variant of project :core-librarys

The project fails to build because the `:core-librarys` module is configured as an Android Application (`com.android.application`) but is being consumed as a dependency by other application modules (`:secretary` and `:student`). Android applications cannot depend on other application modules in this way unless using specific features like Dynamic Delivery, which is not correctly configured here.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///G:/android_app_kotlin/skyAcademy/gradle/libs.versions.toml)
- Add `android-library` plugin definition.

#### [MODIFY] [root build.gradle.kts](file:///G:/android_app_kotlin/skyAcademy/build.gradle.kts)
- Include the `android-library` plugin in the top-level `plugins` block to manage its version centrally.

#### [MODIFY] [core-librarys build.gradle.kts](file:///G:/android_app_kotlin/skyAcademy/core-librarys/build.gradle.kts)
- Switch from `android-application` to `android-library` plugin.
- This will allow the module to be consumed correctly by other modules.

#### [MODIFY] [secretary build.gradle.kts](file:///G:/android_app_kotlin/skyAcademy/secretary/build.gradle.kts)
- Remove `dynamicFeatures.add(":core-librarys")` as it conflicts with the library dependency and is likely a configuration error.

## Verification Plan

### Automated Tests
- Run `./gradlew :secretary:assembleDebug` to verify the build now succeeds.
- Run `./gradlew :student:assembleDebug` to ensure it also builds correctly.

### Manual Verification
- Sync Gradle in Android Studio to ensure no UI-level sync errors persist.
