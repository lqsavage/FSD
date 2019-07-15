import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Video } from '../../model/video';
import { VideoServiceService } from 'src/app/service/video-service.service';


@Component({
  selector: 'app-video-add',
  templateUrl: './video-add.component.html',
  styleUrls: ['./video-add.component.css']
})
export class VideoAddComponent implements OnInit {

  @Output() cancelVideo = new EventEmitter<boolean>();

  videos: [];

  video: Video = {
    id: 6,
    title : '',
    url: '',
    status: '',
    approved: false,
    likes: 0,
    unlike: 0,
    currentStatus: '',
    exitplayprogress: 0

  };

  constructor(private videoservice: VideoServiceService) { }

  ngOnInit() {
  }

  onCancel() {

    this.cancelVideo.emit(false);

  }
  onAddVideo() {
    this.videoservice.addVideoData(this.video).subscribe(data => {
      console.log(data);
    });
  }

}
