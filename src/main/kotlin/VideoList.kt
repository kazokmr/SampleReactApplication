import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.p
import react.fc

external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit) = child(videoList) { this.attrs(handler) }

val videoList = fc<VideoListProps> { props ->
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
