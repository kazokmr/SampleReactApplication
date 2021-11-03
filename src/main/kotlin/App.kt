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

    // StateはKotlinのDelegateプロパティを用いてuseState()モジュールに委譲させると、set関数を宣言しなくても適切なhookの呼び出しでラップしてくれる
    var currentVideo by useState<Video>()
    var unwatchedVideos by useState<List<Video>>(emptyList())
    var watchedVideos by useState<List<Video>>(emptyList())

    // 初回のレンダリングのみで作用させる場合(useEffect(*emptyArray())と等価っぽい。あとemptyList()だとコンパイルエラーになる)
    useEffectOnce {
        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            unwatchedVideos = videos
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
            onSelectVideo = { video -> currentVideo = video }
        }
        h3 {
            +"Videos watched"
        }
        videoList {
            videos = watchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video -> currentVideo = video }
        }
        currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
                unwatchedVideo = currentVideo in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    } else {
                        unwatchedVideos = unwatchedVideos + video
                        watchedVideos = watchedVideos - video
                    }
                }
            }
        }
    }
}
