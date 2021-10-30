import kotlinx.css.Position
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.attrs
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.dom.img
import react.dom.p
import styled.css
import styled.styledDiv

class App : RComponent<Props, State>() {
    override fun RBuilder.render() {
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
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
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
