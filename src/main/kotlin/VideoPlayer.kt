import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.Position
import kotlinx.css.backgroundColor
import kotlinx.css.display
import kotlinx.css.marginBottom
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.dom.attrs
import react.dom.h3
import react.fc
import styled.css
import styled.styledButton
import styled.styledDiv

external interface VideoPlayerProps : Props {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit) = child(videoPlayer) { this.attrs(handler) }

val videoPlayer = fc<VideoPlayerProps> { props ->
    styledDiv {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        styledButton {
            css {
                display = Display.block
                backgroundColor = if (props.unwatchedVideo) Color.lightGreen else Color.red
            }
            attrs {
                onClickFunction = {
                    props.onWatchedButtonPressed(props.video)
                }
            }
            if (props.unwatchedVideo) +"Mark as watched" else +"Mark as unwatched"
        }
        styledDiv {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            emailShareButton {
                attrs.url = props.video.videoUrl
                emailIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
            telegramShareButton {
                attrs.url = props.video.videoUrl
                telegramIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
        }
        reactPlayer {
            attrs.url = props.video.videoUrl
        }
    }
}
