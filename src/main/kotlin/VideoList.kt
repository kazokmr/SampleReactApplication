import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.p

class VideoList : RComponent<Props, State>() {
    override fun RBuilder.render() {
        unwatchedVideos.forEach { video -> p { +"${video.speaker}: ${video.title}" } }
    }
}