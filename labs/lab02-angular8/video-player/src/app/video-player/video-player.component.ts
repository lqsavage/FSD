import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { PlayerComponent } from './components/player';
import { ControlsComponent } from './components/controls';
import { SourceService } from 'src/app/core/services';
import { Source } from 'src/app/core/models';

const VOTE_UP = 'up';
const VOTE_DOWN = 'down';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css'],
})
export class VideoPlayerComponent implements OnInit {
  @ViewChild(PlayerComponent, { static: true })
  playerComponent: PlayerComponent;
  @ViewChild(ControlsComponent, { static: true })
  controlsComponent: ControlsComponent;

  currentSource: Source;
  sources: Source[] = [];

  likes = 0;
  unlikes = 0;
  ratio = 0;

  constructor(private sourceService: SourceService) {}

  ngOnInit() {
    this.sourceService.getApprovedSources().subscribe(sources => {
      this.sources = sources;
    });
  }

  get video() {
    return this.playerComponent.videoRef.nativeElement;
  }

  // player's events
  handleTimeUpdate() {
    // update progress bar
    const video = this.video;
    if (video.duration) {
      this.ratio = +((video.currentTime * 1.0) / video.duration).toFixed(2);
    } else {
      this.ratio = 0;
    }
  }

  handleVideoEnded() {
    this.controlsComponent.stop();
  }

  // control's events
  play() {
    if (this.currentSource) {
      this.video.play();
    }
  }

  pause() {
    this.video.pause();
  }

  stop() {
    this.video.pause();
    this.video.currentTime = 0;
  }

  volume(amount: number) {
    const current = +this.video.volume;
    let expected = current + amount;

    if (expected > 1) {
      expected = 1;
    } else if (expected < 0) {
      expected = 0;
    }

    this.video.volume = expected;
  }

  toggleMute() {
    const muted = this.video.muted;
    this.video.muted = !muted;
  }

  // get value from localStorage
  _getItem(key, defaultValue) {
    return +localStorage.getItem(key) || defaultValue;
  }

  _displayVoteInfo() {
    let key = `${VOTE_UP}@${this.currentSource.id}`;
    this.likes = this._getItem(key, 0);

    key = `${VOTE_DOWN}@${this.currentSource.id}`;
    this.unlikes = this._getItem(key, 0);
  }

  vote(type: string) {
    if (this.currentSource) {
      let key = type === 'up' ? VOTE_UP : VOTE_DOWN;
      key = `${key}@${this.currentSource.id}`;
      const current = this._getItem(key, 0);
      localStorage.setItem(key, current + 1);

      this._displayVoteInfo();
    }
  }

  // playlist's events
  handleSourceSelected(source: Source) {
    this.currentSource = source;
    this.video.src = this.currentSource.url;
    this.controlsComponent.play();
    this._displayVoteInfo();
  }
}
