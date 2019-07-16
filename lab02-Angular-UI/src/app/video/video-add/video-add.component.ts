import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Video } from '../../model/video';


@Component({
  selector: 'app-video-add',
  templateUrl: './video-add.component.html',
  styleUrls: ['./video-add.component.css']
})
export class VideoAddComponent implements OnInit {

  @Output() cancelVideo = new EventEmitter<boolean>();

  @Output() addVideo = new EventEmitter<Video>();
  // videos: Video[];

  video: Video = {
    id: undefined,
    title : '',
    url: '',
    status: '',
    approved: false,
    likes: 0,
    unlike: 0,
    currentStatus: '',
    exitplayprogress: 0

  };

  constructor() { }

  ngOnInit() {
  }

  onCancel() {

    this.cancelVideo.emit(false);

  }
  onAddVideo(video: Video): void {
    this.addVideo.emit(this.video);
  }

}
