import React, { Component } from 'react'
import axios from 'axios'
import { Videoview } from './videoview/Videoview'
import { Videoctrl } from './videoctrl/Videoctrl'
import { Videoadd } from './videoadd/Videoadd'
import { Videolist } from './videolist/Videolist'

export class Video extends Component {

    state = {
        videos: []
    }

    componentDidMount() {
        axios.get('http://localhost:8080/youtube')
        .then(res => this.setState({
            videos : res.data
        }))
    }

    someMethod() {
        console.log('bar');
    }

    // Delete Video
    delVideo = (id) => {
        // console.log(id)
        this.setState({
            videos: [...this.state.videos.filter(video => video.id !== id)]
        })
        axios.delete(`http://localhost:8080/youtube/${id}`)
    }

    // Approve Video
    approveVideo = (video) => {
        video.approved = true
        this.setState({
            videos: [...this.state.videos]
        })
        axios.put(`http://localhost:8080/youtube/${video.id}`, video)
    }

    render() {
        return (
            <div>
                <Videoview />
                <Videoctrl />
                <Videoadd />
                <Videolist videos={this.state.videos} delVideo={this.delVideo} approveVideo={this.approveVideo}/>
            </div>
        )
    }
}

export default Video
