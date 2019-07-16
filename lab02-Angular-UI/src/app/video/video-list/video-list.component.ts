import { Component, Input, Output, EventEmitter } from '@angular/core';
import { VideoServiceService } from 'src/app/service/video-service.service';
import { Video } from '../../model/video';


@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.css']
})
export class VideoListComponent {

  @Input() videos: Video[];

  @Output() addVideo = new EventEmitter<boolean>();

  @Output() delVideoEvent = new EventEmitter<Video>();

  updateVideo: Video;

  constructor(private videoservice: VideoServiceService) {
  }

  loadVideoAddComponent() {
    this.addVideo.emit(true);
 }


 delVideo(video: Video): void {

    this.delVideoEvent.emit(video);
  //  this.videos = this.videos.filter(v => v !== video);
  //  this.videoservice.delVideoData(video).subscribe();
 }

//  editVideo(video: Video): void {
//   this.videoservice.updateVideo(video).subscribe();
// }

edit(video: Video) {
  this.updateVideo = video;
}

save() {
  this.updateVideo.approved = false;
  this.videoservice.updateVideo(this.updateVideo).subscribe(updateVideo => this.updateVideo = updateVideo);
}

approved(video: Video) {
  video.approved = true;
  this.videoservice.updateVideo(video).subscribe();
}

loadVideo(video: Video) {
  console.log(video);
}

}
