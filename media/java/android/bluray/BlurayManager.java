package android.bluray;

/*
*  this file is defined by hh@rock-chips.com
*  BlurayManager���ڶ�����������ĺ�ͻ�ȡ��Ҫ��UI����ʾ�����������Ϣ
*  BlurayManager�е���ز�����Ҫͨ��MediaPlayer����غ���ʵ�֣��䶨�����ر����ͺ��ֵ��Ҫ
*  ��rkBoxPlayerͷ�ļ��еĺ��Ӧ�����BlurayManager�еĺ�ͱ���������ı䣬����ᵼ�²���ʧ��
*  ���ļ��ж���Ĳ���ֻ���ڲ�������ʱ��Ч
*/
import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;
import android.bluray.*;

import android.util.Log;

public class BlurayManager
{
	private final String TAG = "BlurayManager";
	private MediaPlayer mMediaPlayer = null;
	
	// �������������Ϣ��ʼ����Ҫ��RkFrameManage.h�еĶ�Ӧ
	private static final int BLURAY_BASE = 7000;
	// ��ȡ��ǰ���ڲ�����Ƶ��Ϣ
	public static final int BLURAY_GET_VIDEO_INFOR = BLURAY_BASE;
	// ��ȡ��ǰ���ڲ��ŵ�������Ϣ
	public static final int BLURAY_GET_AUDIO_INFOR = BLURAY_BASE+1;
	// ��ȡ��ǰ���ڲ��ŵ���Ļ��Ϣ
	public static final int BLURAY_GET_SUBTITLE_INFOR = BLURAY_BASE+2;

	// ��ȡ��ǰ���ŵ�Title����������
	public static final int BLURAY_GET_AUDIO_TRACK = BLURAY_BASE+3;
	// ��ȡ��ǰ����Title��������Ļ��Ϣ
	public static final int BLURAY_GET_SUBTITLE_TRACK = BLURAY_BASE+4;
	// �����л�
	public static final int BLURAY_SET_AUDIO_TRACK = BLURAY_BASE+5;
	// ��Ļ�л�
	public static final int BLURAY_SET_SUBTITLE_TRACK = BLURAY_BASE+6;
	
	// �������غ���ʾ(ֻ��Pop-up Menu�ܹ����ػ�����ʾ,Always-on Menu����������)
	// ��������BLURAY_SUBTITLE_SHOW/BLURAY_SUBTITLE_HIDE
	public static final int BLURAY_SET_IG_VISIBLE = BLURAY_BASE+7;
	public static final int BLURAY_GET_IG_VISIBLE = BLURAY_BASE+8;
	
	// ��Ļ���غ���ʾ
	// ��������BLURAY_IG_SHOW/BLURAY_IG_HIDE
	public static final int BLURAY_SET_SUBTITLE_VISIBLE = BLURAY_BASE+9;
	public static final int BLURAY_GET_SUBITTLE_VISIBLE = BLURAY_BASE+10;
	
	// ������ť����
	// �������� BLURAY_IG_BUTTON_MOVE_UP
	//          BLURAY_IG_BUTTON_MOVE_DOWN
	//          BLURAY_IG_BUTTON_MOVE_LEFT
	//          BLURAY_IG_BUTTON_MOVE_RIGHT
	//          BLURAY_IG_BUTTON_ACTIVATE
	public static final int BLURAY_SET_IG_OPERATION = BLURAY_BASE+11;

	// ��ȡ��ǰTitle�����½���
	public static final int BLURAY_GET_CHAPTER = BLURAY_BASE+12;
	// ���ŵ�ǰTitle��ĳһ�½�
	public static final int BLURAY_PLAY_CHAPTER = BLURAY_BASE+13;
	// ��ȡ��ǰ���ڲ��ŵ��½�
	public static final int BLURAY_GET_CURRENT_CHAPTER = BLURAY_BASE+14;

	// ��ȡ��ǰTitle��Angle��
	public static final int BLURAY_GET_NUMBER_OF_ANGLE = BLURAY_BASE+15;
	// ��ȡ��ǰTitle�����ڲ��ŵ�Angle
	public static final int BLURAY_GET_CURRENT_ANGLE = BLURAY_BASE+16;
	// ���ŵ�ǰTitle��Angle
	public static final int BLURAY_PLAY_ANGLE = BLURAY_BASE +17;

	/* 
	*  �����Title��Ϊ3��: FirstPlay(AutoRun) Title��Top Menu Title����ͨ��Title
	*  FirstPlay(AutoRun) Title Ϊ�����������ʱ�Զ���ʼ���ŵ�Title
	*  Top Menu Title Ϊ���������Ӧ��Title
	*  ��ͨ��Title Ϊ��������Ƶ���ŵ�Title
	*/
	// ��ȡTitle����,�˴���Title����������FirstPlay(AutoRun) Title��Top Menu Title
	public static final int BLURAY_GET_NUMBER_OF_TITLE = BLURAY_BASE+18;
	// ��ȡ��ǰ���ڲ��ŵ�Title
	public static final int BLURAY_GET_CURRENT_TITLE = BLURAY_BASE+19;
	// ���ò���ĳ��Title
	public static final int BLURAY_PLAY_TITLE = BLURAY_BASE+20;
	// ���ò���Top Title
	public static final int BLURAY_PLAY_TOP_TITLE = BLURAY_BASE+21;

	// ������ǰ�������ݣ����ź�������
	public static final int BLURAY_SKIP_CURRENT_CONTEXT = BLURAY_BASE+22;
	
	public static final int BLURAY_PLAY_SEEK_TIME_PLAY = BLURAY_BASE+23;


	// ��Ļ/��������ʾ
	public static final int BLURAY_SURFACE_SHOW = 1;
	// ��Ļ/����������
	public static final int BLURAY_SURFACE_HIDE = 0;

	// ������ť�ƶ�����ͼ��ֻ��������Ϣ��������Ϣ������
	// �����ƶ�
	public static final int BLURAY_IG_BUTTON_MOVE_UP = 0;  
	// �����ƶ�
	public static final int BLURAY_IG_BUTTON_MOVE_DOWN = 1;
	// �����ƶ�
	public static final int BLURAY_IG_BUTTON_MOVE_LEFT = 2;
	// �����ƶ�
	public static final int BLURAY_IG_BUTTON_MOVE_RIGHT = 3;
	// ���ǰ��ť
	public static final int BLURAY_IG_BUTTON_ACTIVATE = 4;


	// �����ɹ�
	public static final int BLURAY_OPERATION_SUCCESS = 0;
	// ����ʧ��
	public static final int BLURAY_OPERATION_FAIL = 1;
	// ��ֹ��ǰ����
	public static final int BLURAY_OPERATION_FAIL_OPEATION_DISABLE = 2;
	// ����������
	public static final int BLURAY_OPERATION_FAIL_PARAMETER_ERROR = 3;
	// BDJģʽ����֧�ֵ�ǰ�˲���
	public static final int BLURAY_OPERATION_FAIL_BDJ = 3;
	
	public BlurayManager(MediaPlayer player)
	{
		mMediaPlayer = player;

		// �����Ĳ������Ѿ�������OnInfoListener,������BlurayManager�ٴ����ã����򽫻ᵼ�¿ͻ������е�OnInfoListenerʧȥ����
		/*
		if(mMediaPlayer != null)
		{
			mMediaPlayer.setOnBlurayEventListener(mBlurayInforListener);
		}*/
	}

	// ��ȡ��ǰ���ڲ��ŵ���Ƶ�����Ϣ
	public BlurayVideoInfor  getVideoInfor()
	{
		if(mMediaPlayer != null)
		{
			Parcel reply = mMediaPlayer.getParcelParameter(BLURAY_GET_VIDEO_INFOR);
			if(reply != null)
			{
				BlurayVideoInfor videoInfo[] = reply.createTypedArray(BlurayVideoInfor.CREATOR);
				if(videoInfo != null)
				{
					for(int i = 0; i < videoInfo.length; i++)
					{
						videoInfo[i].tostring();
					}
					
					return videoInfo[0];
				}
				
			}
		}
		return null;
	}

	// ��ȡ��ǰ���ڲ��ŵ���Ƶ��Ϣ
	public BlurayAudioInfor  getAudioInfor()
	{
		if(mMediaPlayer != null)
		{
			Parcel reply = mMediaPlayer.getParcelParameter(BLURAY_GET_AUDIO_INFOR);
			if(reply != null)
			{
				BlurayAudioInfor audioInfo[] = reply.createTypedArray(BlurayAudioInfor.CREATOR);
				if((audioInfo != null) && (audioInfo.length > 0))
				{
					for(int i = 0; i < audioInfo.length; i++)
					{
						audioInfo[i].tostring();
					}
					
					return audioInfo[0];
				}
			}
		}
		return null;
	}

	// ��ȡ��ǰ���ڲ�����ʾ����Ļ��Ϣ
	public BluraySubtitleInfor  getSubtitleInfor()
	{
		if(mMediaPlayer != null)
		{
			Parcel reply = mMediaPlayer.getParcelParameter(BLURAY_GET_SUBTITLE_INFOR);
			if(reply != null)
			{
				BluraySubtitleInfor subtitleInfo[] = reply.createTypedArray(BluraySubtitleInfor.CREATOR);;
				if((subtitleInfo != null) && (subtitleInfo.length > 0))
				{
					for(int i = 0; i < subtitleInfo.length; i++)
					{
						subtitleInfo[i].tostring();
					}
					
					return subtitleInfo[0];
				}
			}
		}
		return null;
	}

	// ��ȡ��ǰ��Ƶ����������
	public BlurayAudioInfor[]  getAudioTrack()
	{
		if(mMediaPlayer != null)
		{
			Parcel reply = mMediaPlayer.getParcelParameter(BLURAY_GET_AUDIO_TRACK);
			if(reply != null)
			{
				BlurayAudioInfor trackInfo[] = reply.createTypedArray(BlurayAudioInfor.CREATOR);
				if((trackInfo != null) && (trackInfo.length > 0))
				{
					Log.d(TAG,"Audio Track Count = "+trackInfo.length);
					for(int i = 0; i < trackInfo.length; i++)
					{
						trackInfo[i].tostring();
					}
				}
				return trackInfo;
			}
		}
		return null;
	}

	// ��ȡ��ǰ��Ƶ��������Ļ
	public BluraySubtitleInfor[]  getSubtitleTrack()
	{
		if(mMediaPlayer != null)
		{
            Parcel reply = mMediaPlayer.getParcelParameter(BLURAY_GET_SUBTITLE_TRACK);
			if(reply != null)
			{
				BluraySubtitleInfor trackInfo[] = reply.createTypedArray(BluraySubtitleInfor.CREATOR);
				if((trackInfo != null) && (trackInfo.length > 0))
				{
					Log.d(TAG,"Subtitle Track Count = "+trackInfo.length);
					for(int i = 0; i < trackInfo.length; i++)
					{
						trackInfo[i].tostring();
					}
				}
				return trackInfo;
			}

		}
		return null;
	}

	/*
	* ���ò���ĳ������,�л����죬,�ú������������Ĵ����߳������л����ú���Ϊ�������������л������ͨ��MediaPlayer��onInfo����
	* indexΪBlurayAudioInfor��mIndex��Ӧ��ֵ
	*/
	public boolean  setAudioTrack(int index)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SET_AUDIO_TRACK,index);
		}

		return false;
	}

	/* 
	*  ���ò���ĳ����Ļ,�л���Ļ,�ú������������Ĵ����߳������л����ú���Ϊ�������������л������ͨ��MediaPlayer��onInfo����
	*  indexΪBluraySubtitleInfor��mIndex��Ӧ��ֵ
	*/
	public boolean  setSubtitleTrack(int index)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SET_SUBTITLE_TRACK,index);
		}

		return false;
	}

	/*
	*   ������Ļ����ʾ������
	*   visible: BLURAY_SUBTITLE_SHOW/BLURAY_SUBTITLE_HIDE
	*/
	public boolean setSubtitleSurfaceVisible(int visible)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SET_SUBTITLE_VISIBLE,visible);
		}

		return false;
	}

	/*
	*   ��ȡ��Ļ����ʾ/����״̬
	*   return: BLURAY_SUBTITLE_SHOW/BLURAY_SUBTITLE_HIDE
	*/
	public int getSubtitleSurfaceVisible()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_SUBITTLE_VISIBLE);
		}

		return BLURAY_SURFACE_HIDE;
	}

	/*
	*   ���õ���������ʾ������
	*   visible: BLURAY_IG_SHOW/BLURAY_IG_HIDE
	*/
	public boolean setIGSurfaceVisible(int visible)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SET_IG_VISIBLE,visible);
		}

		return false;
	}

	/*
	*   ��ȡ����������ʾ/����״̬
	*   return: BLURAY_IG_SHOW/BLURAY_IG_HIDE
	*/
	public int getIGSurfaceVisible()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_IG_VISIBLE);
		}

		return BLURAY_SURFACE_HIDE;
	}

	/*
	* ���⵼����ť����,ֻ����ʾ�����˵�ʱ����������Ч
	*
	*/
	public boolean  moveNavigationButton(int direction)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SET_IG_OPERATION,direction);
		}

		return false;
	}

	/*
	* ����:��ȡ��ǰTitle���½�����
	*/
	public int getNumberOfChapter()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_CHAPTER);
		}
		return 0;
	}

	/*
	* ����:���ŵ�ǰTitle��ĳ���½�,
	* param: chapter: 0~getChapterCount()-1
	* ˵��: �ú�����������ⷢ����Ϣ���󲥷ŵ�ǰTitleĳ���½ڣ��ú���Ϊ����������
	*       ����ֵֻ��ʾ�����ͳɹ�����ʧ�ܣ��Ƿ񲥷�ĳ���½ڳɹ�����ʧ�ܽ�ͨ��MediaPlayer.OnInfoListener����
	*/
	public boolean playChapter(int chapter)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_PLAY_CHAPTER,chapter);
		}
		
		return false;
	}

	/*
	* ����:��ȡ��ǰTitle���½�����
	*/
	public int getCurrentChapter()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_CURRENT_CHAPTER);
		}
		
		return 0;
	}

	/*
	* ����:��ȡ��ǰTitle��Angle����
	*/
	public int getNumberOfAngle()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_NUMBER_OF_ANGLE);
		}
		
		return 0;
	}

	/*
	* ����:��ȡ��ǰTitle�����ڲ��ŵ�Angle
	*/
	public int getCurrentAngle()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_CURRENT_ANGLE);
		}
		
		return 0;
	}

	/*
	* ����:���ŵ�ǰTitle��ĳ��Angle��Ӧ����Ƶ
	*/
	public boolean playAngle(int angle)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_PLAY_ANGLE,angle);
		}
		
		return false;
	}

	/*
	* ����:��ȡTitle����
	*/
	public int getNumberOfTitle()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_NUMBER_OF_TITLE);
		}
		
		return 0;
	}

	/*
	* ����:��ȡ��ǰ���ڲ��ŵ�Title
	*/
	public int getCurrentTitle()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.getIntParameter(BLURAY_GET_CURRENT_TITLE);
		}
		
		return 0;
	}

	/*
	* ����:���ŵ�ǰTitle��ĳ��Angle��Ӧ����Ƶ
	*/
	public boolean playTitle(int title)
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_PLAY_TITLE,title);
		}
		
		return false;
	}

	/*
	* ��ת���������沥��
	*/
	public boolean playTopTitle()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_PLAY_TOP_TITLE,0);
		}
		
		return false;
	}

	/*
	* ������ǰ�������ݣ����ź�������
	*/
	public boolean playNextContent()
	{
		if(mMediaPlayer != null)
		{
			return mMediaPlayer.setParameter(BLURAY_SKIP_CURRENT_CONTEXT,0);
		}

		return false;
	}
	
/*
	MediaPlayer.OnBlurayEventListener mBlurayInforListener = new MediaPlayer.OnBlurayEventListener()
	{
		public void onEvent(MediaPlayer mp, int operate,int code,int reson)
		{
			if(mp != null)
			{
				Log.d("BlurayManager","mBlurayInforListener operate = "+operate+",code = "+code+",reson = "+reson);
				switch(operate)
				{
					case BLURAY_SET_IG_OPERATION:
						break;

					case BLURAY_SET_SUBTITLE_VISIBLE:
						break;
						
					case BLURAY_PLAY_CHAPTER:
						break;

					case BLURAY_PLAY_ANGLE:
						break;

					case BLURAY_PLAY_TITLE:
						break;

					case BLURAY_PLAY_TOP_TITLE:
						break;
				}
			}
		}
	};*/
}

