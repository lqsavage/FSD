import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Video } from '../model/video';

@Injectable({
  providedIn: 'root'
})
export class VideoServiceService {

  constructor(private http: HttpClient) { }

  vidoedata = 'assets/video.json';

  video: Video;
  videos: [];

  getVideoData() {
    return this.http.get('http://localhost:3000/youtube');
  }

  addVideoData(video) {
    console.log(video);
    return this.http.post('http://localhost:3000/youtube', video);
  }

}
