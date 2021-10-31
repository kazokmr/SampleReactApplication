import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.attrs
import react.dom.p

external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

class VideoList : RComponent<VideoListProps, State>() {
    override fun RBuilder.render() {
        props.videos.forEach { video ->
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        props.onSelectVideo(video)
                    }
                }
                if (video == props.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit) = child(VideoList::class) { this.attrs(handler) }