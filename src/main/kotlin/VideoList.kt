import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.attrs
import react.dom.p
import react.setState

external interface VideoListProps : Props {
    var videos: List<Video>
}

external interface VideoListState : State {
    var selectedVideo: Video?
}

class VideoList : RComponent<VideoListProps, VideoListState>() {
    override fun RBuilder.render() {
        props.videos.forEach { video ->
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        setState {
                            selectedVideo = video
                        }
                    }
                }
                if (video == state.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit) = child(VideoList::class) { this.attrs(handler) }