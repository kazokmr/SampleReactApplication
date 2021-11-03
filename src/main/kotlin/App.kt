import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.Props
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.fc
import react.useEffectOnce
import react.useState

val app = fc<Props> {
    val (currentVideo, setCurrentVideo) = useState<Video>()
    val (unwatchedVideos, setUnwatchedVideos) = useState<List<Video>>(emptyList())
    val (watchedVideos, setWatchedVideos) = useState<List<Video>>(emptyList())

    // 初回のレンダリングのみで作用させる場合(useEffect(*emptyArray())と等価っぽい。あとemptyList()だとコンパイルエラーになる)
    useEffectOnce {
        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            setUnwatchedVideos(videos)
        }
    }

    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }
        videoList {
            videos = unwatchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video -> setCurrentVideo(video) }
        }
        h3 {
            +"Videos watched"
        }
        videoList {
            videos = watchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video -> setCurrentVideo(video) }
        }
        currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
                unwatchedVideo = currentVideo in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        setUnwatchedVideos(unwatchedVideos - video)
                        setWatchedVideos(watchedVideos + video)
                    } else {
                        setUnwatchedVideos(unwatchedVideos + video)
                        setWatchedVideos(watchedVideos - video)
                    }
                }
            }
        }
    }
}
