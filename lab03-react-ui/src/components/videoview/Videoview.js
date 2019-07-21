import React, { Component } from 'react'
import video2 from '../../assets/video/example2.mp4'
export class Videoview extends Component {

    click = () => {
        this.props.parentMethod();
    }

    // componentDidMount(){
    //     this.listenPlay()
    // }

    listenPlay() {
        document.getElementById("mediaVideo").addEventListener('play', () => console.log('video play'),
        false)
    }
    render() {
        return (
            <div id='video-player' className="VideoPlayer">
            <video id="mediaVideo" controls><source src={video2} type='video/mp4'/></video>
            </div>

        );
    }
}

export default Videoview
