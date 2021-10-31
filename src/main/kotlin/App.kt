import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.setState

external interface AppState : State {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

class App : RComponent<Props, AppState>() {
    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            videoList {
                videos = state.unwatchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
            h3 {
                +"Videos watched"
            }
            videoList {
                videos = state.watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
        }
        state.currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
                unwatchedVideo = currentVideo in state.unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in state.unwatchedVideos) {
                        setState {
                            unwatchedVideos = unwatchedVideos - video
                            watchedVideos = watchedVideos + video
                        }
                    } else {
                        setState {
                            unwatchedVideos = unwatchedVideos + video
                            watchedVideos = watchedVideos - video
                        }
                    }
                }
            }
        }
    }

    override fun AppState.init() {
        unwatchedVideos = listOf()
        watchedVideos = listOf()

        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            setState {
                unwatchedVideos = videos
            }
        }
    }
}
