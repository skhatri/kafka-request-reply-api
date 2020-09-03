rootProject.name="kafka-request-reply-api"

listOf("app").forEach { folder ->
    include(folder)
    project(":${folder}").projectDir = file(folder)
}
