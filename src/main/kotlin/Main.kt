import kotlinx.browser.document
import react.dom.attrs
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.dom.img
import react.dom.p
import react.dom.render

fun main() {

    render(document.getElementById("root")) {
        h1 {
            +"KotlinConf Explorer"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            for (video in unwatchedVideos) {
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }
            h3 {
                +"Videos watched"
            }
            watchedVideos.forEach { video -> p { +"${video.speaker}: ${video.title}" } }
        }
        div {
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}

val unwatchedVideos = listOf<KotlinVideo>(
    KotlinVideo(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    KotlinVideo(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    KotlinVideo(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf<KotlinVideo>(
    KotlinVideo(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)