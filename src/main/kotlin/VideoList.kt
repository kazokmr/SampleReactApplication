import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.p

external interface VideoListProps : Props {
    var videos: List<Video>
}

class VideoList : RComponent<VideoListProps, State>() {
    override fun RBuilder.render() {
        props.videos.forEach { video ->
            p {
                key = video.id.toString()
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}